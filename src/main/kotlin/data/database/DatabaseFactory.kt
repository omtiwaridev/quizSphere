package com.op.data.database

import com.mongodb.kotlin.client.coroutine.MongoClient
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import com.op.data.util.Constants.MONGO_DATABASE_NAME

object DatabaseFactory {
    fun create(): MongoDatabase {
        val connectionString = System.getenv("MONGO_URI") ?: throw IllegalArgumentException("MONGO URI not found")
        val mongoClient = MongoClient.create(connectionString)
        return mongoClient.getDatabase(MONGO_DATABASE_NAME)
    }
}