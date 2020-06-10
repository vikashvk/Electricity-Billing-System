import { Component, OnInit, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginModel } from '../models/login-model';
import { UserAuthService } from '../services/user-auth.service';

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent implements OnInit {
  loginForm: FormGroup;
  isSubmitted: boolean = false;
  private loginDetails: LoginModel;
  get formControls() { return this.loginForm.controls; }
  formErrors = {
    //stores the error messages to show in the form
    'email': '',
    'password': ''
  };
  validationMessages = {
    // stores the predefined error messages
    'email': {
      'required': 'Email is required. ',
      'email': 'Not a valid email. '
    },
    'password': {
      'required': 'Password is required. ',
      'pattern':'Must contain at least one number and one uppercase and lowercase'
    }
  };




  constructor(@Inject('API_URL') public apiUrl, @Inject('GOOGLE_AUTH_URL') public googleAuthUrl, @Inject('FACEBOOK_AUTH_URL') public facebookAuthUrl, private userAuthService: UserAuthService, private formBuilder: FormBuilder, private router: Router) { }

  ngOnInit(): void {
    if (this.userAuthService.isLoggedIn()) {
      this.router.navigateByUrl('/user');
    }
    this.createLoginForm();
    this.loginForm.valueChanges
      .subscribe(data => this.onValueChanged(data));
    this.onValueChanged();
  }

  //creates a reactive form for login
  private createLoginForm() {
    this.loginForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
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
    this.loginDetails = this.loginForm.value;
    // this.loginForm.reset(this.loginForm.value);
  }

  //logs in the user
  //shows error if login in not successful
  //redirects to '/user' if login successful
  login() {
    this.userAuthService.login(this.loginDetails);
  }



}
