package perf.ssh;

import org.apache.commons.cli.*;
import perf.ssh.cmd.CommandDispatcher;
import perf.ssh.config.YamlLoader;
import perf.util.StringUtil;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class SshRunner {

    public static void main(String[] args) {

        Options options = new Options();

        OptionGroup basePathGroup = new OptionGroup();
        basePathGroup.addOption(Option.builder("b")
            .longOpt("basePath")
            .required()
            .hasArg()
            .argName("path")
            .desc("base path for the output folder, creates a new YYYYMMDD_HHmmss sub-folder")
            .build()
        );
        basePathGroup.addOption(Option.builder("B")
            .longOpt("fullPath")
            .required()
            .hasArg()
            .argName("path")
            .desc("full path for the output folder, does not create a sub-folder")
            .build()
        );

        basePathGroup.setRequired(true);

        options.addOptionGroup(basePathGroup);

        options.addOption(
            Option.builder("c")
                .longOpt("commandPool")
                .hasArg()
                .argName("size")
                .type(Integer.TYPE)
                .desc("number of threads for executing commands [24]")
                .build()
        );

        options.addOption(
            Option.builder("C")
                .longOpt("colorTerminal")
                .hasArg(false)
                .desc("flag to enable color formatted terminal")
                .build()
        );


        options.addOption(
            Option.builder("s")
                .longOpt("scheduledPool")
                .hasArg()
                .argName("size")
                .type(Integer.TYPE)
                .desc("number of threads for executing scheduled tasks [4]")
                .build()
        );

        options.addOption(
            Option.builder("S")
                .argName("key=value")
                .desc("set a state parameter")
                .hasArgs()
                .valueSeparator()
                .build()
        );


        options.addOption(
            Option.builder("k")
                .longOpt("knownHosts")
                .desc("ssh known hosts path [~/.ssh/known_hosts]")
                .hasArg()
                .argName("path")
                .type(String.class)
                .build()
        );

        options.addOption(
            Option.builder("p")
                .longOpt("passphrase")
                .desc("ssh passphrase for identify file [null]")
                .hasArgs()
                .optionalArg(true)
                .argName("password")
                .type(String.class)
                .build()
        );
        options.addOption(
            Option.builder("i")
                .longOpt("identity")
                .argName("path")
                .hasArg()
                .desc("ssh identity path [~/.ssh/id_rsa]")
                .type(String.class)
                .build()
        );

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        String cmdLineSyntax = "[options] [yamlFiles]";

        cmdLineSyntax =
            "java -jar " +
            (new File(SshRunner.class
                .getProtectionDomain()
                .getCodeSource()
                .getLocation()
                .getPath()
            )).getName() +
            " " +
            cmdLineSyntax;

        try{
            cmd = parser.parse(options,args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp(cmdLineSyntax,options);
            System.exit(1);
            return;
        }

        int commandThreads = Integer.parseInt(cmd.getOptionValue("commandPool","24"));
        int scheduledThreads = Integer.parseInt(cmd.getOptionValue("scheduledPool","4"));

        List<String> yamlPaths = cmd.getArgList();

        if(yamlPaths.isEmpty()){
            System.out.println("Missing required yaml file(s)");
            formatter.printHelp(cmdLineSyntax,options);
            System.exit(1);
            return;
        }

        YamlLoader loader = new YamlLoader();
        for(String yamlPath : yamlPaths){
            System.out.println("loading: "+yamlPath);
            loader.load(yamlPath);
        }
        if(loader.hasErrors()){
            for(String error : loader.getErrors()){
                System.out.println("Error: "+error);
            }
            System.exit(1);
            return;
        }

        RunConfig config = loader.getRunConfig();

        if (cmd.hasOption("knownHosts") ){
            config.setKnownHosts(cmd.getOptionValue("knownHosts"));
        }
        if (cmd.hasOption("identity") ){
            config.setIdentity(cmd.getOptionValue("identity"));
        }
        if (cmd.hasOption("passphrase") && !cmd.getOptionValue("passphrase").equals( RunConfig.DEFAULT_PASSPHRASE) ){
            config.setPassphrase(cmd.getOptionValue("passphrase"));
        }
        
        if (cmd.hasOption("colorTerminal") ){
            config.setColorTerminal( true );
        }

        Properties stateProps = cmd.getOptionProperties("S");
        if(!stateProps.isEmpty()){
            stateProps.forEach((k,v)->{
                System.out.println("  "+k+" = "+v);
                config.getState().set(k.toString(),v.toString());
            });
        }

        final AtomicInteger factoryCounter = new AtomicInteger(0);
        final AtomicInteger scheduledCounter = new AtomicInteger(0);

        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>();

        ThreadFactory factory = r -> new Thread(r,"command-"+factoryCounter.getAndIncrement());
        ThreadPoolExecutor executor = new ThreadPoolExecutor(commandThreads/2,commandThreads,30, TimeUnit.MINUTES,workQueue,factory);
        ScheduledThreadPoolExecutor scheduled = new ScheduledThreadPoolExecutor(scheduledThreads, runnable -> new Thread(runnable,"scheduled-"+scheduledCounter.getAndIncrement()));

        CommandDispatcher dispatcher = new CommandDispatcher(executor,scheduled);



        String outputPath=null;
        if(cmd.hasOption("basePath")){
            DateTimeFormatter dt = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
            outputPath = cmd.getOptionValue("basePath") + "/" + dt.format(LocalDateTime.now());
        }else if (cmd.hasOption("fullPath")){
            outputPath = cmd.getOptionValue("fullPath");
        }
        String basePath = cmd.getOptionValue("basePath");


        final Run run = new Run(outputPath,config,dispatcher);

        System.out.println("Starting with output path = "+run.getOutputPath());

        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            if(!run.isAborted()) {
                run.abort();
            }
        },"shutdown-abort"));

        long start = System.currentTimeMillis();

        run.run();



        long stop = System.currentTimeMillis();

        System.out.println("Finished in "+ StringUtil.durationToString(stop-start)+" at "+run.getOutputPath());

        dispatcher.shutdown();
        executor.shutdownNow();
        scheduled.shutdownNow();

    }
}
