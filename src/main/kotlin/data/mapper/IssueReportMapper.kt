package com.op.data.mapper

import com.op.data.database.entity.IssueReportEntity
import com.op.domain.model.IssueReport

fun IssueReportEntity.toIssueReport()  = IssueReport(
    id = _id,
    questionId = questionId,
    issueType = issueType,
    additionalComment = additionalComment,
    userEmail = userEmail,
    timestamp = timestamp
)

fun IssueReport.toIssueReportEntity() = IssueReportEntity(
    questionId = questionId,
    issueType = issueType,
    additionalComment = additionalComment,
    userEmail = userEmail,
    timestamp = timestamp
)