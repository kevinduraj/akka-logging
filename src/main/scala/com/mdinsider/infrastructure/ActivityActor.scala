package com.mdinsider.infrastructure

import akka.actor.{ Actor, ActorLogging, Props }
import com.mdinsider.domain.ActivityRepository
import com.mdinsider.domain.Protocol._

object ActivityActor {
  def props(repo: ActivityRepository): Props = Props(new ActivityActor(repo))
}

class ActivityActor(repo: ActivityRepository) extends Actor with ActorLogging {

  override def postStop() = {
    repo.close()
  }

  def receive: Receive = {

    case CreateSchema() =>
      log.info("------------  CreateSchema ------------")
      repo.create_keyspace()

    case CreateTable() =>
      log.info("------------  CreateTable ------------")
      repo.create_table()

    case AddActivity(activity) =>
      log.info("------------  AddActivity ------------")
      repo.insertRecord(activity)
  }

}
