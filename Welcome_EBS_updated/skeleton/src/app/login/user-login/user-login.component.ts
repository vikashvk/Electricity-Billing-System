import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserAuthService } from '../../services/user-auth.service';
import { Router } from '@angular/router';
import { LoginModel } from '../login-model';
import { AuthService, SocialUser, GoogleLoginProvider, FacebookLoginProvider } from 'angularx-social-login';

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent implements OnInit {
  user: SocialUser;
  loginForm: FormGroup;
  isSubmitted: boolean = false;
  private loginDetails: LoginModel;
  get formControls() { return this.loginForm.controls; }
  formErrors = {
    //stores the error messages to show in the form
    'mobile': '',
    'password': ''
  };
  validationMessages = {
    // stores the predefined error messages
    'mobile': {
      'required': 'Email is required. ',
    },
    'password': {
      'required': 'Password is required. '
    }
  };



 
  constructor(private userAuthService: UserAuthService, private socialAuthService: AuthService, private formBuilder: FormBuilder, private router: Router) { }

  ngOnInit(): void {
    this.createLoginForm();
    this.loginForm.valueChanges
      .subscribe(data => this.onValueChanged(data));
    this.onValueChanged();
  }

  //creates a reactive form for login
  private createLoginForm() {
    this.loginForm = this.formBuilder.group({
      mobile: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  //called for validation when any value is changed in the form
  private onValueChanged(data?: any): void {
    if (!this.loginForm) { return; }
    const form = this.loginForm;
    for (const field in this.formErrors) {
      if (this.formErrors.hasOwnProperty(field)) {
        this.formErrors[field] = '';
        const control = form.get(field);
        if (control && control.dirty && !control.valid) {
          for (const key in control.errors) {
            if (control.errors.hasOwnProperty(key)) {
              this.formErrors[field] += this.validationMessages[field][key] + ' ';
            }
          }
        }
      }
    }
    this.loginDetails = { username: this.loginForm.value.mobile, password: this.loginForm.value.password }
      ;
  }

  //logs in the user
  //shows error if login in not successful
  //redirects to '/user' if login successful
  login() {
    this.userAuthService.login(this.loginDetails);
  }

  //Social Login Logics
  signInWithSocialSite(loginProvider: string): void {
    let providerId: string;
    if (loginProvider == "google") {
      providerId = GoogleLoginProvider.PROVIDER_ID
    }
    else if (loginProvider == "facebook") {
      providerId = FacebookLoginProvider.PROVIDER_ID
    }
    else {
      return;
    }
    this.socialAuthService.signIn(providerId).then(response => {
      this.onSuccessfulSocialSignIn();
    });
  }

  onSuccessfulSocialSignIn() {
    this.socialAuthService.authState.subscribe((user) => {
      this.user = user;
      console.log(user);
    });
  }

  signOut(): void {
    this.socialAuthService.signOut();
  }

 

}
