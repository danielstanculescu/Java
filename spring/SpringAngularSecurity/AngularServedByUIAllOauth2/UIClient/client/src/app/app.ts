import { Component, inject } from '@angular/core'
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-root',
  standalone: true,
  templateUrl: './app.html',
  styleUrls: ['./app.css']
})


export class App{

  title = 'OAuth2 Client Application';
  counter :number = 0;

  private readonly http = inject(HttpClient);

  constructor() {

  }


  async sayHello(): Promise<void> {
    try{

      /* not used anymore with gateway server
      const token: string = await this.appService.fetchSessionIdToken();

      const headers = new HttpHeaders({
        'X-Auth-Token': token
      });
      */

      //this.http.get<{ text: string }>('/resource/demo/hello', { headers })
      //this.http.get<{ text: string }>('http://localhost:8082/demo/hello', { headers })
      this.http.get<{ text: string }>('/resource/demo/hello')
        .subscribe({
          next:(value) => {
            alert(value.text);
          },
          error: (err) => {
            console.error('HTTP Error:', err);
          }
        });
    }
    catch(authError) {
      console.error('Authentication Error:', authError);
    }
  }


  getHello(): void {

      //this.http.get<{ text: string }>('/resource/demo/hello', { headers })
      //this.http.get<{ text: string }>('http://localhost:8082/demo/hello', { headers })
      this.http.get<{ text: string }>('/resource/hello')
        .subscribe({
          next:(value) => {
            alert(value.text);
          },
          error: (err) => {
            console.error('HTTP Error:', err);
          },
          complete: ():void => {
            console.log('Completed.')
          }
        });
    }

  postHello(): void {

    //this.http.get<{ text: string }>('/resource/demo/hello', { headers })
    //this.http.get<{ text: string }>('http://localhost:8082/demo/hello', { headers })

    const textToSend: string = 'Hello ' + this.counter;

    this.http.post('/resource/hello', {text: textToSend})
      .subscribe({
        next:(value) => {
          console.log(value)
          alert(JSON.stringify(value, undefined, 4));
        },
        error: (err) => {
          console.error('HTTP Error:', err);
        },
        complete: ():void => {
          console.log('Completed.')
        }
      });
  }

}
