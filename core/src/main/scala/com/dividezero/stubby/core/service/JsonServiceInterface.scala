package com.dividezero.stubby.core.service

import com.dividezero.stubby.core.util.JsonUtils
import com.dividezero.stubby.core.model.StubExchange
import java.io.InputStream
import java.io.OutputStream
import com.dividezero.stubby.core.model.StubRequest

class JsonServiceInterface(service: StubService) { // service wrapper using serialized JSON strings/streams

  def addResponse(exchange: String) =
    service.addResponse(JsonUtils.deserialize[StubExchange](exchange).nilLists) // nilLists: <HACK/>

  def addResponse(stream: InputStream) =
    service.addResponse(JsonUtils.deserialize[StubExchange](stream).nilLists) // nilLists: <HACK/>

  def getResponse(index: Int) =
    JsonUtils.serialize(service.getResponse(index))

  def getResponse(stream: OutputStream, index: Int) =
    JsonUtils.serialize(stream, service.getResponse(index))

  def getResponses(): String =
    JsonUtils.serialize(service.responses)

  def getResponses(stream: OutputStream) =
    JsonUtils.serialize(stream, service.responses)

  @throws[NotFoundException]("if index does not exist")
  def deleteResponse(index: Int) =
    service.deleteResponse(index)

  def deleteResponses() =
    service.deleteResponses()

  @throws[NotFoundException]("if index does not exist")
  def getRequest(index: Int): String =
    JsonUtils.serialize(service.getRequest(index))

  @throws[NotFoundException]("if index does not exist")
  def getRequest(stream: OutputStream, index: Int) =
    JsonUtils.serialize(stream, service.getRequest(index))

  def getRequests(): String =
    JsonUtils.serialize(service.requests)

  def getRequests(stream: OutputStream) =
    JsonUtils.serialize(stream, service.requests)

  def findRequests(filter: StubRequest): String =
    JsonUtils.serialize(service.findRequests(filter))

  def findRequests(filter: StubRequest, wait: Long): String =
    JsonUtils.serialize(service.findRequests(filter, wait))

  def findRequest(stream: OutputStream, filter: StubRequest) =
    JsonUtils.serialize(stream, service.findRequests(filter))

  @throws[NotFoundException]("if index does not exist")
  def deleteRequest(index: Int) =
    service.deleteRequest(index)

  def deleteRequests() =
    service.deleteRequests()

}