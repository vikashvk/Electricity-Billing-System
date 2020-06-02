import { Injectable, ErrorHandler } from '@angular/core';
import { UNAUTHORIZED, BAD_REQUEST, FORBIDDEN } from "http-status-codes";
import { Router } from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class ErrorHandlerService implements ErrorHandler{
  static readonly DEFAULT_ERROR_TITLE: string = "Something went wrong";

  constructor(private router: Router) { }


  public handleError(error: any) {
    console.log(error);
    let httpErrorCode = error.status;
    switch (httpErrorCode) {
      case UNAUTHORIZED:
        this.showError(error.error.message);
        // this.router.navigateByUrl("/hello");
        break;
      case FORBIDDEN:
        this.router.navigateByUrl("/unauthorized");
        break;
      case BAD_REQUEST:
        this.showError(error.message);
        break;
      default:
        this.showError(ErrorHandlerService.DEFAULT_ERROR_TITLE);
    }
  }

  private showError(message: string) {
    console.log("Inside showError:", message);
  }
}
