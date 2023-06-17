import { Injectable } from '@angular/core';
import { ErrorResponse } from '../models/error-response';
import { HttpErrorResponse } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ErrorService {

  constructor() { }

  public handleError(name: string, error: any): Promise<any> {
    console.log(`Error occured in '` + name +`':`)
    console.log(error);
    alert(`Operation failed with message: ${error?.message}`)
    
    throw error;
  }

  public handleConnectionError(response: HttpErrorResponse): Promise<any> {
    console.log(response);
    const error = response.error;
    if (error.title) {
      alert(error.title);
    }

    throw error;
  }
}
