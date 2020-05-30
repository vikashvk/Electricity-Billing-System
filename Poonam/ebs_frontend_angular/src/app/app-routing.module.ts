import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from './services/auth.guard';
import { CustomerModule } from './customer/customer.module';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';


const routes: Routes = [{ path: '', redirectTo: 'login', pathMatch: 'full' },
{
  path: 'user'
  // , loadChildren: './customer/customer.module#CustomerModule'
  , loadChildren: () => CustomerModule
  // , canActivateChild:[AuthGuard]
},
{ path: '**', component: PageNotFoundComponent }];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
