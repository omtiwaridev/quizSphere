package com.op.presentation.routes

import com.op.domain.model.IssueReport
import com.op.domain.repository.IssueReportRepository
import com.op.domain.util.onFailure
import com.op.domain.util.onSuccess
import com.op.presentation.routes.path.IssueReportRoutesPath
import com.op.presentation.util.respondWithError
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.resources.delete
import io.ktor.server.resources.get
import io.ktor.server.resources.post
import io.ktor.server.response.respond
import io.ktor.server.routing.Route

fun Route.issueReportRoutes(repository: IssueReportRepository){
    post<IssueReportRoutesPath> {
        val report = call.receive<IssueReport>()
        repository.insertIssueReport(report)
            .onSuccess {
                call.respond(
                    message = "Report submitted Successfully",
                    status = HttpStatusCode.Created
                )
            }
            .onFailure { error ->
                respondWithError(error)
            }
    }

    get<IssueReportRoutesPath> {
        repository.getAllIssueReports()
            .onSuccess { reports ->
                call.respond(message = reports,
                    status = HttpStatusCode.OK)
            }
            .onFailure { error ->
                respondWithError(error)
            }
    }

    delete<IssueReportRoutesPath.ById> { path ->
        repository.deleteIssueReportById(path.reportId)
            .onSuccess {
                call.respond(message = "Report Deleted Successfully",
                    status = HttpStatusCode.NoContent)
            }
            .onFailure {  error ->
                respondWithError(error)
            }
    }
}