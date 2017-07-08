package com.mdinsider.domain

import scala.concurrent.Future

trait ActivityRepository {

  def create_keyspace(): Future[Unit]

  def create_table(): Future[Unit]

  def insertRecord(activity: Activity): Future[Unit]

  def close(): Unit

}
