import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';
import { map, catchError, tap } from 'rxjs/operators';
import { Observable, throwError } from 'rxjs';

import { Router } from '@angular/router';
import { Producto } from '../facturas/models/producto';
import { Almacen } from './models/almacen';

@Injectable({
  providedIn: 'root'
})
export class AlmacenService {

  private urlEndPoint: string = 'http://localhost:8080/api/almacenes';

  constructor(private http: HttpClient, private router: Router) { }

  getAlmacenes(page: number): Observable<any> {
    return this.http.get(this.urlEndPoint + '/page/' + page).pipe(
      tap((response: any) => {
        console.log('AlmacenService: tap 1');
        (response.data as Almacen[]).forEach(almacen => console.log(almacen.nombre));
      }),
      map((response: any) => {
        (response.data as Almacen[]).map(almacen => {
          almacen.nombre = almacen.nombre.toUpperCase();
          return almacen;
        });
        return response;
      }),
      tap(response => {
        console.log('AlmacenService: tap 2');
        (response.data as Almacen[]).forEach(almacen => console.log(almacen.nombre));
      }));
  }

  create(almacen: Almacen): Observable<Almacen> {
    return this.http.post(this.urlEndPoint, almacen)
      .pipe(
        map((response: any) => response as Almacen),
        catchError(e => {
          if (e.status == 400) {
            return throwError(e);
          }
          if (e.error.mensaje) {
            console.error(e.error.mensaje);
          }
          return throwError(e);
        }));
  }

  getAlmacen(id): Observable<Almacen> {
    return this.http.get<Almacen>(`${this.urlEndPoint}/${id}`).pipe(
      catchError(e => {
        if (e.status != 401 && e.error.mensaje) {
          this.router.navigate(['/almacenes']);
          console.error(e.error.mensaje);
        }

        return throwError(e);
      }));
  }

  update(almacen: Almacen): Observable<Almacen> {
    return this.http.put<any>(`${this.urlEndPoint}`, almacen).pipe(
      catchError(e => {
        if (e.status == 400) {
          return throwError(e);
        }
        if (e.error.mensaje) {
          console.error(e.error.mensaje);
        }
        return throwError(e);
      }));
  }

  delete(id: string): Observable<Almacen> {
    return this.http.delete<Almacen>(`${this.urlEndPoint}/${id}`).pipe(
      catchError(e => {
        if (e.error.mensaje) {
          console.error(e.error.mensaje);
        }
        return throwError(e);
      }));
  }

  filtrarProductos(term: string): Observable<Producto[]> {
    return this.http.get<Producto[]>(`${this.urlEndPoint}/filtrar-productos/${term}`);
  }

}
