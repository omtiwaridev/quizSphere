package com.op.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class IssueReport(val id: String? = null,
                       val questionId: String,
                       val issueType: String,
                       val additionalComment: String?,
                       val userEmail: String?,
                       val timestamp: String)

