package com.axkea.review.core.examine;

import com.axkea.review.review.common.ExamineEvent;

public interface ExamineHandler {

    boolean examine(ExamineEvent context, String type);
    boolean examineContent(ExamineEvent context);
    boolean examineAudio(ExamineEvent context);
    boolean examineImg(ExamineEvent context);
    boolean examineVideo(ExamineEvent context);
}
