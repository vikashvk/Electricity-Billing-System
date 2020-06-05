import { Injectable, ErrorHandler } from '@angular/core';
import { UNAUTHORIZED, BAD_REQUEST, FORBIDDEN } from "http-status-codes";
import { Router } from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class ErrorHandlerService implements ErrorHandler {
  static readonly DEFAULT_ERROR_TITLE: string = "Please try again.";
  static readonly UNAUTHORIZED_ERROR: string = "Please login to access.";
  constructor(private router: Router) { }


  public handleError(error: any) {
    console.log(error);
    let httpErrorCode = error.status;
    switch (httpErrorCode) {
      case UNAUTHORIZED:
        this.showError(ErrorHandlerService.UNAUTHORIZED_ERROR);
        this.router.navigateByUrl("/login");
        break;
      case BAD_REQUEST:
        let message: string = '';
        if (error.error) {
          message = error.error.message;
          this.showError(message);
        }
        break;
      case 0:
        break;
      default:
        this.showError(ErrorHandlerService.DEFAULT_ERROR_TITLE);
    }
  }

  private showError(message: string) {
    alert(message);
  }
}
