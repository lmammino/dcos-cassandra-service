package com.mesosphere.dcos.cassandra.common.tasks.backup;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mesosphere.dcos.cassandra.common.CassandraProtos;
import com.mesosphere.dcos.cassandra.common.tasks.CassandraTask;
import com.mesosphere.dcos.cassandra.common.tasks.CassandraTaskStatus;
import org.apache.mesos.Protos;

import java.util.Optional;

public class BackupSnapshotStatus extends CassandraTaskStatus {
    @JsonCreator
    public static BackupSnapshotStatus create(
            @JsonProperty("state") Protos.TaskState state,
            @JsonProperty("id") String id,
            @JsonProperty("slave_id") String slaveId,
            @JsonProperty("executor_id") String executorId,
            @JsonProperty("message") Optional<String> message) {
        return new BackupSnapshotStatus(state, id, slaveId, executorId, message);
    }

    protected BackupSnapshotStatus(Protos.TaskState state,
                                   String id,
                                   String slaveId,
                                   String executorId,
                                   Optional<String> message) {
        super(CassandraTask.TYPE.BACKUP_SNAPSHOT,
                state,
                id,
                slaveId,
                executorId,
                message);
    }

    @Override
    public BackupSnapshotStatus update(Protos.TaskState state) {
        return create(state, id, slaveId, executorId, message);
    }

    @Override
    protected CassandraProtos.CassandraTaskStatusData getData() {
        return CassandraProtos.CassandraTaskStatusData.newBuilder()
                .setType(CassandraProtos.CassandraTaskData.TYPE.BACKUP_SNAPSHOT)
                .build();
    }
}
