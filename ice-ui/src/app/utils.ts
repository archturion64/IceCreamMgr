import { Observable, OperatorFunction, UnaryFunction, filter, pipe } from "rxjs";

function isUndefinedAndNotNull<T>(value: T): value is NonNullable<T> {
    return typeof(value) !== 'undefined' && value !== null;
} 

export function filterUndefinedAndNull<T>(): UnaryFunction<Observable<T | null | undefined>, Observable<T>> {
    return pipe(
        filter(x => isUndefinedAndNotNull(x)) as OperatorFunction<T | null | undefined, T>
    )
}

