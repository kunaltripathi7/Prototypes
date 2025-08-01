import { Injectable } from '@nestjs/common';
import { Prisma, Role } from '@prisma/client';
import { DatabaseService } from 'src/database/database.service';

@Injectable()
export class EmployeesService {
  // injecting the database service || privatae readOnly so that it can't be accessed outside
  constructor(private readonly databaseService: DatabaseService) {}
  async create(createEmployeeDto: Prisma.EmployeeCreateInput) {
    //running on client(prisma)
    return this.databaseService.employee.create({
      data: createEmployeeDto,
    });
  }

  async findAll(role?: Role) {
    if (role)
      return this.databaseService.employee.findMany({
        where: {
          role,
        },
      });
    return this.databaseService.employee.findMany();
  }

  async findOne(id: number) {
    return this.databaseService.employee.findUnique({
      where: {
        id,
      },
    });
  }

  async update(id: number, updateEmployeeDto: Prisma.EmployeeUpdateInput) {
    return this.databaseService.employee.update({
      where: {
        id,
      },
      data: updateEmployeeDto,
    });
  }

  async remove(id: number) {
    return this.databaseService.employee.delete({
      where: {
        id,
      },
    });
  }
}

// NestJS scans the application and identifies the EmployeesService as an injectable provider.
// When another component (e.g., a controller) requests an instance of the EmployeesService, NestJS checks the constructor of the EmployeesService to determine its dependencies.
// NestJS identifies that the EmployeesService depends on the DatabaseService.
// NestJS checks if an instance of the DatabaseService is already available in the dependency injection container. If not, it creates a new instance of the DatabaseService.
// NestJS passes the instance of the DatabaseService to the constructor of the EmployeesService when creating the EmployeesService instance.
// The EmployeesService instance can now use the DatabaseService instance to interact with the database.
