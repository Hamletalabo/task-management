package org.hamlet.taskmgt.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskResponse {

    private String responseCode;

    private String responseMessage;

    private TaskInfo taskInfo;
}
