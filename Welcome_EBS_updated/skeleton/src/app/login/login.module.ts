import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FacebookLoginProvider, GoogleLoginProvider, AuthServiceConfig, SocialLoginModule, AuthService } from 'angularx-social-login';
import { RouterModule, Routes } from '@angular/router';
import { UserLoginComponent } from './user-login/user-login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { GOOGLE_OAUTH_CLIENT_ID, FACEBOOK_OAUTH_CLIENT_ID } from '../../client-secrets';


let config = new AuthServiceConfig([
  {
    id: GoogleLoginProvider.PROVIDER_ID,
    provider: new GoogleLoginProvider(GOOGLE_OAUTH_CLIENT_ID)
  },
  {
    id: FacebookLoginProvider.PROVIDER_ID,
    provider: new FacebookLoginProvider(FACEBOOK_OAUTH_CLIENT_ID)
  }
]);
export function provideConfig() {
  return config;
}

const routes: Routes = [{ path: 'login', component: UserLoginComponent }];
@NgModule({
  declarations: [UserLoginComponent],
  imports: [
    CommonModule,
    SocialLoginModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule.forRoot(routes)
  ],
  providers: [
    {
      provide: AuthServiceConfig,
      useFactory: provideConfig
    }
  ]
})
export class LoginModule { }
