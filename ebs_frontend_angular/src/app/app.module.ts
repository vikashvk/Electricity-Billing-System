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
import {API_BASE_URL, GOOGLE_AUTH_URL, FACEBOOK_AUTH_URL } from '../constants';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RedirectHandlerComponent } from './redirect-handler/redirect-handler.component';
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
    AdminpageComponent,UserLoginComponent, RedirectHandlerComponent,

  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [{ provide: 'API_URL', useValue: API_BASE_URL },{ provide: 'GOOGLE_AUTH_URL', useValue: GOOGLE_AUTH_URL },{ provide: 'FACEBOOK_AUTH_URL', useValue: FACEBOOK_AUTH_URL }, { provide: ErrorHandler, useClass: ErrorHandlerService }, {
    provide: HTTP_INTERCEPTORS,
    useClass: TokenInterceptorService,
    multi: true
  }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }