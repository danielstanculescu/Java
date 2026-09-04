import { Component, inject, signal } from '@angular/core'
import { FormsModule } from '@angular/forms'
import { Router } from '@angular/router'
import { AppService } from '../app/app.service'

@Component({
  selector: 'app-login',
  standalone: true, // Replaces NgModule declaration
  imports: [FormsModule], // Local template dependencies
  templateUrl: './login.html',
  styleUrls: ['./login.css']
})
export class LoginComponent {
  // Service dependencies injected via modern functional injection
  private readonly appService = inject(AppService)
  private readonly router = inject(Router)

  // Standard object mutable directly by [(ngModel)] bindings
  credentials = {
    username: '',
    password: ''
  }

  // Reactive state using modern Angular Signals
  error = signal<string>('')

  async signIn (): Promise<boolean> {
    this.error.set('') // Reset the signal state
    try {
      const user = await this.appService.authenticate(this.credentials)
      console.log(user)
      await this.router.navigateByUrl('/')
    } catch (error) {
      this.error.set('Invalid username / password') // Update the signal state
      console.error(error)
    }
    return false
  }
}