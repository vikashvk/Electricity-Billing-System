import { BrowserModule } from '@angular/platform-browser';
import { NgModule, ErrorHandler } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavigationbarComponent } from './navigationbar/navigationbar.component';
import { HeaderComponent } from './header/header.component';
import { IntroComponent } from './intro/intro.component';
import { ServiceebsComponent } from './serviceebs/serviceebs.component';
import { TeamsComponent } from './teams/teams.component';
import { AboutComponent } from './about/about.component';
import { FooterComponent } from './footer/footer.component';
import { AdminComponent } from './admin/admin.component';
import { HomeComponent } from './home/home.component';
import { AdminpageComponent } from './adminpage/adminpage.component';
import { ErrorHandlerService } from './services/error-handler.service';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { TokenInterceptorService } from './services/token-interceptor.service';
import { UserLoginComponent } from './user-login/user-login.component';
import { API_BASE_URL, GOOGLE_AUTH_URL, FACEBOOK_AUTH_URL } from '../constants';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RedirectHandlerComponent } from './redirect-handler/redirect-handler.component';
import { UserRegistrationComponent } from './user-registration/user-registration.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { IonicModule } from '@ionic/angular';
import { LogoutComponent } from './logout/logout.component';
import { CreditCardFormComponent } from './credit-card-form/credit-card-form.component';
import { AddBillViewComponent } from './bills/add-bill-view/add-bill-view.component';
import { BillviewComponent } from './bills/billview/billview.component';
import { CustomerviewComponent } from './bills/customerview/customerview.component';
import { UpdateBillViewComponent } from './bills/update-bill-view/update-bill-view.component';
@NgModule({
  declarations: [
    AppComponent,
    NavigationbarComponent,
    HeaderComponent,
    IntroComponent,
    ServiceebsComponent,
    TeamsComponent,
    AboutComponent,
    FooterComponent,
    AdminComponent,
    HomeComponent,
    AddBillViewComponent,
    BillviewComponent,
    CustomerviewComponent,
    UpdateBillViewComponent,
    AdminpageComponent, UserLoginComponent, RedirectHandlerComponent, UserRegistrationComponent, PageNotFoundComponent, LogoutComponent, CreditCardFormComponent,

  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    AppRoutingModule,
    IonicModule
  ],
  providers: [{ provide: 'API_URL', useValue: API_BASE_URL }, { provide: 'GOOGLE_AUTH_URL', useValue: GOOGLE_AUTH_URL }, { provide: 'FACEBOOK_AUTH_URL', useValue: FACEBOOK_AUTH_URL }, { provide: ErrorHandler, useClass: ErrorHandlerService }, {
    provide: HTTP_INTERCEPTORS,
    useClass: TokenInterceptorService,
    multi: true
  }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
