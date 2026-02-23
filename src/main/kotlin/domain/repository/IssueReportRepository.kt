package com.op.domain.repository

import com.op.domain.model.IssueReport
import com.op.domain.util.DataError
import com.op.domain.util.Result

interface IssueReportRepository {
    suspend fun getAllIssueReports(): Result<List<IssueReport>, DataError>
    suspend fun insertIssueReport(report: IssueReport): Result<Unit, DataError>
    suspend fun deleteIssueReportById(id: String?): Result<Unit, DataError>
}