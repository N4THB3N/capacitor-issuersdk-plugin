import type { IssuerGooglePay } from './definitions';
import { IContainsToken, IGooglePayToken, IProvisionByEncryptedCard, IProvisionByPushReceiptId, IProvisionInfo, IProvisioningResult, IssuerGpApi, ITokenizeGP, IUserAddress, Status, DefaultErrorApi, TokenStatus } from './definitions';
declare const IssuerGP: IssuerGooglePay;
export * from './definitions';
export { IssuerGP, IssuerGpApi, IContainsToken, IGooglePayToken, IProvisioningResult, IUserAddress, IProvisionInfo, IProvisionByEncryptedCard, IProvisionByPushReceiptId, ITokenizeGP, TokenStatus, DefaultErrorApi, Status, };
