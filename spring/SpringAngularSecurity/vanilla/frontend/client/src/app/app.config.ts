import { ApplicationConfig, provideZonelessChangeDetection } from '@angular/core';
import { provideRouter, Routes } from '@angular/router';
import { provideHttpClient, withInterceptors } from '@angular/common/http'; // <-- Import these
import { AppService } from './app.service';
import { xhrInterceptor } from './xhr-interceptor.service';
import { BankComponent } from '../bank/bank';
import { LoginComponent } from '../login/login';


const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'bank' },
  { path: 'bank', component: BankComponent },
  { path: 'login', component: LoginComponent }
]


export const appConfig: ApplicationConfig = {
  providers: [
    provideZonelessChangeDetection(),
    provideRouter(routes),
    // Functional interceptor replaces HTTP_INTERCEPTORS class multi provider
    provideHttpClient(withInterceptors([xhrInterceptor])), 
    AppService 
  ]
};
