package com.op.di

import com.op.data.database.DatabaseFactory
import com.op.data.repository_impl.IssueReportRepositoryImpl
import com.op.data.repository_impl.QuizQuestionRepositoryImpl
import com.op.data.repository_impl.QuizTopicRepositoryImpl
import com.op.domain.repository.IssueReportRepository
import com.op.domain.repository.QuizQuestionRepository
import com.op.domain.repository.QuizTopicRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val koinModule = module {
    single { DatabaseFactory.create() }
    singleOf(::QuizQuestionRepositoryImpl).bind<QuizQuestionRepository>()
    singleOf(::QuizTopicRepositoryImpl).bind<QuizTopicRepository>()
    singleOf(::IssueReportRepositoryImpl).bind<IssueReportRepository>()
}