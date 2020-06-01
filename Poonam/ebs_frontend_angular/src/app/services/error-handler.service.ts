import { Injectable, ErrorHandler } from '@angular/core';
import { UNAUTHORIZED, BAD_REQUEST, FORBIDDEN } from "http-status-codes";
import { Router } from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class ErrorHandlerService implements ErrorHandler {
  static readonly REFRESH_PAGE_ON_TOAST_CLICK_MESSAGE: string = "An error occurred: Please click this message to refresh";
  static readonly DEFAULT_ERROR_TITLE: string = "Something went wrong";

  constructor(private router: Router) { };


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
        this.showError(ErrorHandlerService.REFRESH_PAGE_ON_TOAST_CLICK_MESSAGE);
    }
  }

  private showError(message: string) {
    console.log("inside showError")
    // this.toastManager.error(message, ErrorHandlerService.DEFAULT_ERROR_TITLE, { dismiss: 'controlled' }).then((toast: Toast) => {
    //   let currentToastId: number = toast.id;
    //   this.toastManager.onClickToast().subscribe(clickedToast => {
    //     if (clickedToast.id === currentToastId) {
    //       this.toastManager.dismissToast(toast);
    //       window.location.reload();
    //     }
    //   });
    // });
    console.log("Inside showError:", message);
  }
}