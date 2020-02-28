package com.example.app

import com.example.app.repository.BlocksRepository
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.ScalatraServlet
import org.scalatra.json.JacksonJsonSupport

class BlocksController extends ScalatraServlet with JacksonJsonSupport {

  // Sets up automatic case class to JSON output serialization, required by
  // the JValueResult trait.
  protected implicit lazy val jsonFormats: Formats = DefaultFormats

  // Before every action runs, set the content type to be in JSON format.
  before() {
    contentType = formats("json")
  }

  get("/blocks/:from/:to") {
    BlocksRepository.getBlocksInRange(params("from").toInt, params("to").toInt)
  }

  get("/blocks/:number") {
    BlocksRepository.getBlockByNumber(params("number").toInt)
  }

  get("/blocks/:number/transactions") {
    BlocksRepository.getTransactionsBlockByNumber(params("number").toInt)
  }

}
