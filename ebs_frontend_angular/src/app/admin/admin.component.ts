import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AdminModel } from '../models/admin-model';
import { Router } from '@angular/router';
import { AdminService } from '../services/admin.service';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  // loginForm: FormGroup
  username = 'admin'
  password = ''
  invalidLogin = false
  // admin: AdminModel;
  constructor(private formBuilder: FormBuilder, private router: Router, private adminService: AuthenticationService) { }
ngOnInit():void{}
checkLogin() {
  if (this.adminService.authenticate(this.username, this.password)
  ) {
    this.router.navigate(['/adminp'])
    this.invalidLogin = false
  } else
    this.invalidLogin = true
}

}
  // ngOnInit(): void {
  //   this.loginForm = this.formBuilder.group({
  //     adminName: ['', Validators.required],
  //     password: ['', Validators.required]
  //   });
  // }
  // onSubmit() {
  //   debugger
  //   if (this.loginForm.invalid == true) {
  //     console.log("Wrong")
  //     return
  //   }
  //   this.adminService.login(this.loginForm.controls.adminName.value,
  //     this.loginForm.controls.password.value).subscribe(response => {
  //       debugger
  //       if (response) {
  //         this.router.navigate(['/adminp']);
  //       } else {
  //         alert('wrong user');
  //         this.loginForm.reset();
  //       }
  //     })
  // }


// }

