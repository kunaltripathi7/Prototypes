import { Logger } from '@nestjs/common';
import { MigrationInterface, QueryRunner } from 'typeorm';

export class $npmConfigName1725559915597 implements MigrationInterface {
  private readonly logger = new Logger($npmConfigName1725559915597.name);
  public async up(queryRunner: QueryRunner): Promise<void> {
    this.logger.log('UP');
    await queryRunner.query('UPDATE item SET public = 1');
  }

  public async down(queryRunner: QueryRunner): Promise<void> {
    this.logger.log('Down');
  }
}
