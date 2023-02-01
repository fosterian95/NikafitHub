import { Position } from "../enum/position.enum";

export interface User {
    id: number;
    firstName: string;
    lastName: string;
    position: Position;
}