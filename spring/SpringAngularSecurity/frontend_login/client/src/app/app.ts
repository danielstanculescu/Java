import { Component, inject } from '@angular/core'
import { Router, RouterOutlet } from '@angular/router'
import { AppService } from './app.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
  templateUrl: './app.html',
  styleUrls: ['./app.css']
})


export class App{

  title = 'Spring Security with Angular';

  private readonly appService = inject(AppService);
  private readonly router = inject(Router);
  private readonly http = inject(HttpClient);

  constructor() {
    // 3. Application initialization logic executes safely inside the native constructor
    this.appService.fetchAuthenticationState()
      .then(isAuthenticated => {
        if (!isAuthenticated) {
          void this.router.navigateByUrl('/login');
        }
      })
      .catch(error => {
        console.error('Authentication verification failed:', error);
        void this.router.navigateByUrl('/login');
      });
  }

  logout(): void {
    
    void this.http.post('/logout', {})
    .subscribe(() : void => {
      this.appService.authenticated.set(false);
      this.router.navigateByUrl('');
    })
  }
}
  
  
