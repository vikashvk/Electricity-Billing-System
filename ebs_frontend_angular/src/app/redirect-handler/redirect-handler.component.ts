import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ACCESS_TOKEN } from 'src/constants';

@Component({
  selector: 'app-redirect-handler',
  templateUrl: './redirect-handler.component.html',
  styleUrls: ['./redirect-handler.component.css']
})
export class RedirectHandlerComponent implements OnInit {
  token: string;
  error: string;
  constructor(private activatedRoute: ActivatedRoute, private router: Router) {

  }
  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(params => {
      this.token = params['token'];
      this.error = params['error'];
      if (this.token) {
        localStorage.setItem(ACCESS_TOKEN,this.token);
        this.router.navigateByUrl('/user');
      }
      else {
        this.router.navigateByUrl('/login');
      }
    });
  }

}
