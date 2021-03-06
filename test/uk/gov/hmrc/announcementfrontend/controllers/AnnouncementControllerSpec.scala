/*
 * Copyright 2018 HM Revenue & Customs
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


import org.jsoup.Jsoup
import org.scalatest.mockito.MockitoSugar
import org.scalatestplus.play.OneAppPerSuite
import play.api.Application
import play.api.i18n.Messages.Implicits.applicationMessages
import play.api.test.FakeRequest
import uk.gov.hmrc.announcementfrontend.config.AppConfig
import uk.gov.hmrc.announcementfrontend.views.html.sa_filing_notice_2018
import uk.gov.hmrc.play.test.UnitSpec

class AnnouncementControllerSpec extends UnitSpec with MockitoSugar with OneAppPerSuite {

  private val mockAppConfig = app.injector.instanceOf[AppConfig]
  override implicit lazy val app: Application = fakeApplication()

  "Announcements sa-filing-notice-2018 html" should {

    "contain a webchat link with webchat status " in {
      val result = Jsoup.parse(sa_filing_notice_2018()(FakeRequest("GET", "/"), applicationMessages, mockAppConfig).toString())

      result.body().toString should include("Need help with completing your tax return?")
      result.body().toString should include("Tell HMRC about an employee's")
      result.body().toString should include("Webinar")
      result.body().toString should include("There are several live sessions this month so you can")
      result.body().toString should include("Talk to an adviser online")
    }
  }
}
