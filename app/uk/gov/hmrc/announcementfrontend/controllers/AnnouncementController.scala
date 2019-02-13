/*
 * Copyright 2019 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.announcementfrontend.controllers

import javax.inject.{Inject, Singleton}
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc.{Action, AnyContent}
import play.api.{Configuration, Environment}
import uk.gov.hmrc.announcementfrontend.config.AppConfig
import uk.gov.hmrc.announcementfrontend.controllers.actions.AuthActions
import uk.gov.hmrc.announcementfrontend.views.html
import uk.gov.hmrc.auth.core.AuthConnector
import uk.gov.hmrc.play.bootstrap.controller.FrontendController

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class AnnouncementController @Inject()(val messagesApi: MessagesApi, implicit val appConfig: AppConfig, runModeConfiguration: Configuration, environment: Environment, override val authConnector: AuthConnector, ec: ExecutionContext)
  extends AuthActions with FrontendController with I18nSupport {

  def saFillingNotice2018: Action[AnyContent] = AuthorisedForAnnouncement(ec).async { implicit announcementRequest =>
    Future.successful(Ok(html.sa_filing_notice_2018()))
  }

  def enquiry: Action[AnyContent] = Action {
    Redirect(appConfig.twoWayMessageEnquiryFrontend)
  }

  override def config: Configuration = runModeConfiguration

  override def env: Environment = environment
}
