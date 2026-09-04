import { HttpClient } from '@angular/common/http'
import { Component, inject, signal, computed } from '@angular/core'
import { FormsModule } from '@angular/forms'
import { AppService } from '../app/app.service'

@Component({
  selector: 'bank-home',
  standalone: true, // Replaces NgModule declaration
  imports: [FormsModule], // Allows [(ngModel)] binding in the template
  templateUrl: './bank.html',
  styleUrls: ['./bank.css']
})
export class BankComponent {
  // Service dependencies injected via modern functional injection
  private readonly http = inject(HttpClient)
  private readonly appService = inject(AppService)

  // Standard object mutable directly by [(ngModel)] template bindings
  transfer = {
    to: '',
    amount: 0
  }

  // Reactive computed state tracking authentication natively via Signals
  // Assumes appService.authenticated is converted to a Signal. 
  // If it is a normal boolean property, use: computed(() => this.appService.authenticated)
  authenticated = computed(() => this.appService.authenticated())

  handleAccountChange (event: Event): void {
    const target = event.target as HTMLInputElement
    this.transfer.to = target.value
  }

  handleAmountChange (event: Event): void {
    const target = event.target as HTMLInputElement
    this.transfer.amount = parseInt(target.value, 10) || 0
  }

  transferMoney (): void {
    this.http.get('/banking/transfer', {
      params: {
        account: this.transfer.to,
        amount: this.transfer.amount
      }
    }).subscribe({
      next: (response) => console.log(response),
      error: (err) => console.error(err)
    })
  }

  transferMoneyPost (): void {
    this.http.post('/banking/transfer', {
      account: this.transfer.to,
      amount: this.transfer.amount
    }).subscribe({
      next: (response) => console.log(response),
      error: (err) => console.error(err)
    })
  }
}