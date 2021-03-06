import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdminComponent } from './admin/admin.component';
import { HomeComponent } from './home/home.component';
import { AdminpageComponent } from './adminpage/adminpage.component';
import { UserLoginComponent } from './user-login/user-login.component';
import { RedirectHandlerComponent } from './redirect-handler/redirect-handler.component';
import { CustomerModule } from './customer/customer.module';
import { UserRegistrationComponent } from './user-registration/user-registration.component';
import { AuthGuardService } from './services/auth-guard.service';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { LogoutComponent } from './logout/logout.component';
import { CreditCardFormComponent } from './credit-card-form/credit-card-form.component';
import { UpdateBillViewComponent } from './bills/update-bill-view/update-bill-view.component';
import { BillviewComponent } from './bills/billview/billview.component';
import { AddBillViewComponent } from './bills/add-bill-view/add-bill-view.component';
import { CustomerviewComponent } from './bills/customerview/customerview.component';
import { ViewallbillsComponent } from './bills/viewallbills/viewallbills.component';
import { ViewallCustomersComponent } from './bills/viewall-customers/viewall-customers.component';
import { IntroComponent } from './intro/intro.component';
import { ServiceebsComponent } from './serviceebs/serviceebs.component';
import { TeamsComponent } from './teams/teams.component';
import { AboutComponent } from './about/about.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';


const routes: Routes = [
  {
    path: '', redirectTo: '/home', pathMatch: 'full'
  },
  {
    path: 'home', component: HomeComponent
  },
  {
    path: 'ourIntro', component: IntroComponent
    
    },
    {
    path: 'ourServices', component: ServiceebsComponent
  },
   {
    path: 'ourTeam', component: TeamsComponent
  },
   {
    path: 'ourAbout', component: AboutComponent
  },
  {
    path: 'admin', component: AdminComponent
  },
  {
    path: 'adminp', component: AdminpageComponent
  },
  {
    path: 'login', component: UserLoginComponent
  },
  
  {
    path: 'reset-password', component: ResetPasswordComponent
  },
  {
    path: 'payment', component: CreditCardFormComponent
  },

  {
    path: 'register', component: UserRegistrationComponent
  },
  {
    path: 'oauth2-redirect', component: RedirectHandlerComponent
  },
  {
    path: 'logout', component: LogoutComponent
  },
  {
    path: 'user'
    , loadChildren: () => import('./customer/customer.module').then(m => m.CustomerModule)
    , canActivateChild:[AuthGuardService]
  },
  {
    path: 'customerView', component: CustomerviewComponent
  },
  {
    path: 'addbill/:custId', component: AddBillViewComponent
  },
  {
    path: 'billdetails/:id', component: BillviewComponent
  },
  {
    path: 'updateBill/:id', component: UpdateBillViewComponent
  }
  ,
  {
    path: 'viewallbill', component: ViewallbillsComponent
  },
  {
    path: 'ViewallCustomer', component: ViewallCustomersComponent
  },
  {
    path: '**', component: PageNotFoundComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
