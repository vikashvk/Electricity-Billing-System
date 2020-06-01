import { BrowserModule } from '@angular/platform-browser';
import { NgModule, ErrorHandler } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { LoginModule } from './login/login.module';
import { CustomerModule } from './customer/customer.module';

import { AppComponent } from './app.component';
import { apiUrl } from '../api-url';
import { ErrorHandlerService } from './services/error-handler.service';
import { TokenInterceptorService } from './services/token-interceptor.service';
@NgModule({
  declarations: [AppComponent
  ],
  imports: [BrowserModule,
    LoginModule,
    CustomerModule,
    HttpClientModule,
    AppRoutingModule,
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