import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdminComponent } from './admin/admin.component';
import { HomeComponent } from './home/home.component';
import { AdminpageComponent } from './adminpage/adminpage.component';
import { UserLoginComponent } from './login/user-login/user-login.component';


const routes: Routes = [
  {
    path: '', redirectTo: '/home', pathMatch: 'full'
  },
  {
    path: 'home', component: HomeComponent
  },
  {
    path: 'admin', component: AdminComponent
  },
  {
    path: 'adminp', component: AdminpageComponent
  },
  {
    path: 'login', component: UserLoginComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
