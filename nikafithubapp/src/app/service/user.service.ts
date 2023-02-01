import { Injectable } from '@angular/core'
import { HttpClient } from '@angular/common/http'
import { CustomResponse } from '../interface/custom-response';
import { Observable, throwError } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';
import { User } from '../interface/user';

@Injectable({
    providedIn: 'root'
})
export class UserService {
    private readonly apiUrl = 'any';

    constructor(private http: HttpClient) {}

    users$ = <Observable<CustomResponse>>
    this.http.get<CustomResponse>(`${this.apiUrl}/users`)
    .pipe(
        tap(console.log),
        catchError(this.handleError)
    );

    user$ = (userId: number) => <Observable<CustomResponse>>
    this.http.get<CustomResponse>(`${this.apiUrl}/users/${userId}`)
    .pipe(
        tap(console.log),
        catchError(this.handleError)
    );

    save$ = (user: User) => <Observable<CustomResponse>>
    this.http.post<CustomResponse>(`${this.apiUrl}/users`, user)
    .pipe(
        tap(console.log),
        catchError(this.handleError)
    );

    delete$ = (userId: number) => <Observable<CustomResponse>>
    this.http.delete<CustomResponse>(`${this.apiUrl}/users/${userId}`)
    .pipe(
        tap(console.log),
        catchError(this.handleError)
    );

    handleError(handleError: any): Observable<never> {
        return throwError ('Method not implemented.');
    }
}