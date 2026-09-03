import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable, inject, signal } from '@angular/core';
import { firstValueFrom } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class AppService {
  private readonly http = inject(HttpClient);

  // 1. Convert mutable property to a read-only Angular Signal for template reactivity
  #authenticated = signal(false);
  authenticated = this.#authenticated;

  // 2. Streamlined with native firstValueFrom for clean async/await patterns
  async fetchAuthenticationState(): Promise<boolean> {
    try {
      const response = await firstValueFrom(this.http.get<{ name: string }>('/user'));
      const isValid = response?.name != null;
      this.#authenticated.set(isValid);
      return isValid;
    } catch (error) {
      this.#authenticated.set(false);
      throw error;
    }
  }

  async authenticate(credentials: { username: string; password: string }): Promise<{ name: string }> {
    const headers = new HttpHeaders({
      authorization: `Basic ${btoa(`${credentials.username}:${credentials.password}`)}`
    });

    try {
      const response = await firstValueFrom(this.http.get<{ name: string }>('/user', { headers }));
      this.#authenticated.set(response?.name != null);
      return response;
    } catch (error) {
      this.#authenticated.set(false);
      throw error;
    }
  }

/* old version
  async fetchSessionIdToken(): Promise<string> {
    return await new Promise((resolve, reject) => {
      this.http.get<{ token: string }>('/token')
      .subscribe({
        next: value => {
          resolve(value.token)
          },
        error: err => {
          reject(err)
          }
        })
      })
      }
    }
*/
  async fetchSessionIdToken(): Promise<string> {
      try {
        // firstValueFrom takes the first emitted value and converts it to a Promise
        const res = await firstValueFrom(this.http.get<{ token: string }>('/token'));
        return res.token;
      } catch (err) {
        console.error('Failed to fetch session token:', err);
        throw err;
      }
    }

}
