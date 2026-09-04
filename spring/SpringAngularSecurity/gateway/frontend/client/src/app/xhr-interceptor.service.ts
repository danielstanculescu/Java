import { type HttpEvent, type HttpHandlerFn, type HttpInterceptorFn, type HttpRequest } from '@angular/common/http'
import { type Observable } from 'rxjs'

export const xhrInterceptor: HttpInterceptorFn = (
  req: HttpRequest<unknown>,
  next: HttpHandlerFn
): Observable<HttpEvent<unknown>> => {

  const xhr = req.clone({
    headers: req.headers.set('X-Requested-With', 'XMLHttpRequest')
    //.set('X-abc','def')

  })

  return next(xhr)
}
