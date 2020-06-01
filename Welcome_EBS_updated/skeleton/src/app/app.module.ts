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
import { apiUrl } from '../api-url';
import { ErrorHandlerService } from './services/error-handler.service';
import { HttpClientModule, HTTP_INTERCEPTORS } from '../../node_modules/@angular/common/http';
import { TokenInterceptorService } from './services/token-interceptor.service';
import { LoginModule } from './login/login.module';

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
    AdminpageComponent
  ],
  imports: [
    BrowserModule,
    LoginModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [{ provide: 'API_URL', useValue: apiUrl }, { provide: ErrorHandler, useClass: ErrorHandlerService }, {
    provide: HTTP_INTERCEPTORS,
    useClass: TokenInterceptorService,
    multi: true
  }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
