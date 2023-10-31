package com.axkea.review.review.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * @author welsir
 * @date 2023/10/26 23:33
 **/

/**
 * 审核事件
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExamineEvent {
    private String eventId = UUID.randomUUID().toString();
    private String url;         //检测的文件路径
    private String content;     //检测的文本内容

    private String successUrl;
    private String failUrl;
}
