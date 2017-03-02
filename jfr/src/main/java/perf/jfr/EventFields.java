package perf.jfr;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by wreicher
 */
public class EventFields {

    private static final Map<String,List<String>> MAP = new HashMap<>();

    static {
        MAP.put("java/error_throw",new CopyOnWriteArrayList<>(Arrays.asList(
                "(startTime)",
                "(endTime)",
                "(duration)",
                "(thread)",
                "((thread).javaThreadId)",
                "((thread).osThreadId)",
                "(stackTrace)",
                "((stackTrace).frame)",
                "((stackTrace).package)",
                "((stackTrace).class)",
                "((stackTrace).method)",
                "((stackTrace).almostThreadRootframe)",
                "thrownClass",
                "(thrownClass.package)",
                "message"
        )));
        MAP.put("java/exception_throw",Arrays.asList());
        MAP.put("java/file_read",Arrays.asList(
                "(startTime)",
                "(endTime)",
                "(duration)",
                "(thread)",
                "((thread).javaThreadId)",
                "((thread).osThreadId)",
                "(stackTrace)",
                "((stackTrace).frame)",
                "((stackTrace).package)",
                "((stackTrace).class)",
                "((stackTrace).method)",
                "((stackTrace).almostThreadRootframe)",
                "bytesRead",
                "path"
        ));
        MAP.put("java/file_write",Arrays.asList(
                "(startTime)",
                "(endTime)",
                "(duration)",
                "(thread)",
                "((thread).javaThreadId)",
                "((thread).osThreadId)",
                "(stackTrace)",
                "((stackTrace).frame)",
                "((stackTrace).package)",
                "((stackTrace).class)",
                "((stackTrace).method)",
                "((stackTrace).almostThreadRootframe)",
                "bytesWritten",
                "path"
        ));
        MAP.put("java/monitor_enter",Arrays.asList(
                "(startTime)",
                "(endTime)",
                "(duration)",
                "(thread)",
                "((thread).javaThreadId)",
                "((thread).osThreadId)",
                "(stackTrace)",
                "((stackTrace).frame)",
                "((stackTrace).package)",
                "((stackTrace).class)",
                "((stackTrace).method)",
                "((stackTrace).almostThreadRootframe)",
                "klass",
                "(klass.package)",
                "previousOwner",
                "address"
        ));
        MAP.put("java/monitor_wait",Arrays.asList(
                "(startTime)",
                "(endTime)",
                "(duration)",
                "(thread)",
                "((thread).javaThreadId)",
                "((thread).osThreadId)",
                "(stackTrace)",
                "((stackTrace).frame)",
                "((stackTrace).package)",
                "((stackTrace).class)",
                "((stackTrace).method)",
                "((stackTrace).almostThreadRootframe)",
                "klass",
                "(klass.package)",
                "notifier",
                "(notifier.javaThreadId)",
                "(notifier.osThreadId)",
                "timeout",
                "timedOut",
                "address"
        ));
        MAP.put("java/object_alloc_in_new_TLAB",Arrays.asList());
        MAP.put("java/object_alloc_outside_TLAB",Arrays.asList());
        MAP.put("java/socket_read",Arrays.asList(
                "(startTime)",
                "(endTime)",
                "(duration)",
                "(thread)",
                "((thread).javaThreadId)",
                "((thread).osThreadId)",
                "(stackTrace)",
                "((stackTrace).frame)",
                "((stackTrace).package)",
                "((stackTrace).class)",
                "((stackTrace).method)",
                "((stackTrace).almostThreadRootframe)",
                "bytesRead",
                "timeout",
                "port",
                "address",
                "host"
        ));
        MAP.put("java/socket_write",Arrays.asList(
                "(startTime)",
                "(endTime)",
                "(duration)",
                "(thread)",
                "((thread).javaThreadId)",
                "((thread).osThreadId)",
                "(stackTrace)",
                "((stackTrace).frame)",
                "((stackTrace).package)",
                "((stackTrace).class)",
                "((stackTrace).method)",
                "((stackTrace).almostThreadRootframe)",
                "bytesWritten",
                "port",
                "address",
                "host"
        ));
        MAP.put("java/statistics/class_loading",Arrays.asList());
        MAP.put("java/statistics/thread_allocation",Arrays.asList());
        MAP.put("java/statistics/threads",Arrays.asList(
                "(startTime)",
                "(endTime)",
                "(duration)",
                "activeCount",
                "daemonCount",
                "accumulatedCount",
                "peakCount"
        ));
        MAP.put("java/statistics/throwables",Arrays.asList(
                "(startTime)",
                "(endTime)",
                "(duration)",
                "(thread)",
                "((thread).javaThreadId)",
                "((thread).osThreadId)",
                "throwables"
        ));
        MAP.put("java/thread_end",Arrays.asList(
                "(startTime)",
                "(endTime)",
                "(duration)",
                "(thread)",
                "((thread).javaThreadId)",
                "((thread).osThreadId)",
                "javalangthread"
        ));
        MAP.put("java/thread_park",Arrays.asList(
                "(startTime)",
                "(endTime)",
                "(duration)",
                "(thread)",
                "((thread).javaThreadId)",
                "((thread).osThreadId)",
                "((stackTrace)",
                "((stackTrace).frame)",
                "((stackTrace).package)",
                "((stackTrace).class)",
                "((stackTrace).method)",
                "((stackTrace).almostThreadRootframe)",
                "klass",
                "(klass.package)",
                "timeout",
                "address"
        ));
        MAP.put("java/thread_sleep",Arrays.asList(
                "(startTime)",
                "(endTime)",
                "(duration)",
                "(thread)",
                "((thread).javaThreadId)",
                "((thread).osThreadId)",
                "(stackTrace)",
                "((stackTrace).frame)",
                "((stackTrace).package)",
                "((stackTrace).class)",
                "((stackTrace).method)",
                "((stackTrace).almostThreadRootframe)",
                "time"
        ));
        MAP.put("java/thread_start",Arrays.asList(
                "(startTime)",
                "(endTime)",
                "(duration)",
                "(thread)",
                "((thread).javaThreadId)",
                "((thread).osThreadId)",
                "javalangthread"
        ));
        MAP.put("os/information",Arrays.asList());
        MAP.put("os/initial_environment_variable",Arrays.asList());
        MAP.put("os/memory/physical_memory",Arrays.asList(
                "(startTime)",
                "(endTime)",
                "(duration)",
                "totalSize",
                "usedSize"
        ));
        MAP.put("os/processor/context_switch_rate",Arrays.asList(
                "(startTime)",
                "(endTime)",
                "(duration)",
                "switchRate"
        ));
        MAP.put("os/processor/cpu_information",Arrays.asList(
                "(startTime)",
                "(endTime)",
                "(duration)",
                "cpu",
                "description",
                "sockets",
                "cores",
                "hwThreads"
        ));
        MAP.put("os/processor/cpu_load",Arrays.asList(
                "(startTime)",
                "(endTime)",
                "(duration)",
                "jvmUser",
                "jvmSystem",
                "machineTotal"));
        MAP.put("os/processor/cpu_tsc",Arrays.asList());
        MAP.put("os/system_process",Arrays.asList(
                "(startTime)",
                "(endTime)",
                "(duration)",
                "pid",
                "commandLine"));
        MAP.put("recordings/bufer_lost",Arrays.asList());
        MAP.put("recordings/recording",Arrays.asList());
        MAP.put("recordings/recording_setting",Arrays.asList());
        MAP.put("vm/class/load",Arrays.asList());
        MAP.put("vm/class/unload",Arrays.asList());
        MAP.put("vm/code_cache/config",Arrays.asList());
        MAP.put("vm/code_cache/full",Arrays.asList());
        MAP.put("vm/code_cache/stats",Arrays.asList());
        MAP.put("vm/code_sweeper/config",Arrays.asList());
        MAP.put("vm/code_sweeper/stats",Arrays.asList());
        MAP.put("vm/code_sweeper/sweep",Arrays.asList());
        MAP.put("vm/compiler/compilation",Arrays.asList());
        MAP.put("vm/compiler/config",Arrays.asList());
        MAP.put("vm/compiler/failure",Arrays.asList());
        MAP.put("vm/compiler/phase",Arrays.asList());
        MAP.put("vm/compiler/stats",Arrays.asList());
        MAP.put("vm/flag/boolean",Arrays.asList());
        MAP.put("vm/flag/boolean_changed",Arrays.asList());
        MAP.put("vm/flag/double",Arrays.asList());
        MAP.put("vm/flag/double_changed",Arrays.asList());
        MAP.put("vm/flag/long",Arrays.asList());
        MAP.put("vm/flag/long_changed",Arrays.asList());
        MAP.put("vm/flag/string",Arrays.asList());
        MAP.put("vm/flag/string_changed",Arrays.asList());
        MAP.put("vm/flag/ulong",Arrays.asList());
        MAP.put("vm/flag/ulong_changed",Arrays.asList());
        MAP.put("vm/gc/collector/g1_garbage_collection",Arrays.asList());
        MAP.put("vm/gc/collector/garbage_collection",Arrays.asList(
                "(startTime)",
                "(endTime)",
                "(duration)",
                "gcId",
                "name",
                "cause",
                "sumOfPauses",
                "longestPause"
        ));
        MAP.put("vm/gc/collector/old_garbage_collection",Arrays.asList(
                "(startTime)",
                "(endTime)",
                "(duration)",
                "gcId"
        ));
        MAP.put("vm/gc/collector/parold_garbage_collection",Arrays.asList(
                "(startTime)",
                "(endTime)",
                "(duration)",
                "gcId",
                "densePrefix"
        ));
        MAP.put("vm/gc/collector/young_garbage_collection",Arrays.asList(
                "(startTime)",
                "(endTime)",
                "(duration)",
                "gcId",
                "tenuringThreshold"
        ));
        MAP.put("vm/gc/coniguration/gc",Arrays.asList(
                "(startTime)",
                "(endTime)",
                "(duration)",
                "youngCollector",
                "oldCollector",
                "parallelGCThreads",
                "concurrentGCThreads",
                "usesDynamicGCThreads",
                "isExplicitGCConcurrent",
                "isExplicitGCDisabled",
                "pauseTarget",
                "gcTimeRatio"
        ));
        MAP.put("vm/gc/coniguration/heap",Arrays.asList(
                "(startTime)",
                "(endTime)",
                "(duration)",
                "minSize",
                "maxSize",
                "initialSize",
                "usesCompressedOops",
                "compressedOopsMode",
                "objectAlignment",
                "heapAddressBits"
        ));
        MAP.put("vm/gc/coniguration/survivor",Arrays.asList(
                "(startTime)",
                "(endTime)",
                "(duration)",
                "maxTenuringThreshold",
                "initialTenuringThreshold"
        ));
        MAP.put("vm/gc/coniguration/tlab",Arrays.asList(
                "(startTime)",
                "(endTime)",
                "(duration)",
                "usesTLABs",
                "minTLABSize",
                "tlabRefillWasteLimit"
        ));
        MAP.put("vm/gc/coniguration/young_generation",Arrays.asList(
                "(startTime)",
                "(endTime)",
                "(duration)",
                "minSize",
                "maxSize",
                "newRatio"
        ));
        MAP.put("vm/gc/detailed/allocation_requiring_gc",Arrays.asList());
        MAP.put("vm/gc/detailed/concurrent_mode_failure",Arrays.asList());
        MAP.put("vm/gc/detailed/evacuation_failed",Arrays.asList());
        MAP.put("vm/gc/detailed/evacuation_info",Arrays.asList());
        MAP.put("vm/gc/detailed/object_count",Arrays.asList(
                "(startTime)",
                "(endTime)",
                "(duration)",
                "gcId",
                "class",
                "(class.package)",
                "count",
                "totalSize"));
        MAP.put("vm/gc/detailed/object_count_after_gc",Arrays.asList());
        MAP.put("vm/gc/detailed/promotion_failed",Arrays.asList());
        MAP.put("vm/gc/heap/metaspace_summary",Arrays.asList());
        MAP.put("vm/gc/heap/ps_summary",Arrays.asList(
                "(startTime)",
                "(endTime)",
                "(duration)",
                "gcId",
                "when",
                "oldSpace:start",
                "oldSpace:committedEnd",
                "oldSpace:committedSize",
                "oldSpace:reservedEnd",
                "oldSpace:reservedSize",
                "edenSpace:start",
                "edenSpace:end",
                "edenSpace:used",
                "edenSpace:size",
                "fromSpace:start",
                "fromSpace:end",
                "fromSpace:end",
                "fromSpace:size",
                "toSpace:start",
                "toSpace:end",
                "toSpace:used",
                "toSpace:size"));
        MAP.put("vm/gc/heap/summary",Arrays.asList(
                "(startTime)",
                "(endTime)",
                "(duration)",
                "gcId",
                "when",
                "heapSpace:start",
                "heapSpace:committedEnd",
                "heapSpace:committedSize",
                "heapSpace:reservedEnd",
                "heapSpace:reservedSize",
                "heapUsed"));
        MAP.put("vm/gc/metaspace/allocation_failure",Arrays.asList());
        MAP.put("vm/gc/metaspace/chunk_free_list_summary",Arrays.asList());
        MAP.put("vm/gc/metaspace/gc_threshold",Arrays.asList());
        MAP.put("vm/gc/metaspace/out_of_memory",Arrays.asList());
        MAP.put("vm/gc/phase/pause",Arrays.asList(
                "(startTime)",
                "(endTime)",
                "(duration)",
                "gcId",
                "name"));
        MAP.put("vm/gc/phase/pause_level_1",Arrays.asList(
                "(startTime)",
                "(endTime)",
                "(duration)",
                "gcId",
                "name"));
        MAP.put("vm/gc/phase/pause_level_2",Arrays.asList(
                "(startTime)",
                "(endTime)",
                "(duration)",
                "gcId",
                "name"));
        MAP.put("vm/gc/phase/pause_level_3",Arrays.asList());
        MAP.put("vm/gc/reference/statistics",Arrays.asList(
                "(startTime)",
                "(endTime)",
                "(duration)",
                "gcId",
                "type",
                "count"));
        MAP.put("vm/info",Arrays.asList());
        MAP.put("vm/initial_system_property",Arrays.asList());
        MAP.put("vm/prof/execution_sample",Arrays.asList());
        MAP.put("vm/prof/execution_sampling_info",Arrays.asList());
        MAP.put("vm/runtime/execute_vm_operation",Arrays.asList(
                "(startTime)",
                "(endTime)",
                "(duration)",
                "(thread)",
                "((thread).javaThreadId)",
                "((thread).osThreadId)",
                "operation",
                "safepoint",
                "blocking",
                "caller",
                "(caller.javaThreadId)",
                "(caller.osThreadId)"));
        MAP.put("vm/runtime/thread_dump",Arrays.asList(
                "(startTime)",
                "(endTime)",
                "(duration)",
                "result"));
    }
    public static List<String> get(String key){
        return MAP.containsKey(key) ? MAP.get(key) : Collections.emptyList();
    }
    public static Set<String> getEvents(){return MAP.keySet();}
}
