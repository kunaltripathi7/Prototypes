import { CreateUserDto } from './create-user.dto';
import { PartialType } from '@nestjs/mapped-types';

// same as ts
export class UpdateUserDto extends PartialType(CreateUserDto) {}
