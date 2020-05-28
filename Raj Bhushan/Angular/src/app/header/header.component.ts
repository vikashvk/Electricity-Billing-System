import { Component, OnInit } from '@angular/core';
import { LoginComponent } from '../login/login.component';
import { RegisterComponent } from '../register/register.component';
import { AuthenticationService } from '../services/authentication.service';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  constructor(public dialog:MatDialog, public authenticationService:AuthenticationService) { }

  openLoginForm() {
    this.dialog.open(LoginComponent, {width: '300px', height: '350px'});
  }

  openRegisterForm()
  {
    this.dialog.open(RegisterComponent,{width: '300px', height: '450px'});
  }
  ngOnInit(): void {
  }

}
