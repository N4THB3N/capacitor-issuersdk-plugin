export enum Status {
  SUCCESS = 'SUCCESS',
  FAILURE = 'FAILURE',
}

export interface IssuerGpApi<ResponseType> {
  result: Status;
  data: ResponseType;
}

export interface DefaultErrorApi {
  statusCode: string;
  message: string;
}

export interface IGooglePayToken {
  tokenReferenceId: string;
  issuerName: string;
  physicalPanLast4: string;
  tokenLast4: string;
  tokenServiceProvider: TokenServiceProvider;
  cardNetwork: CardNetwork;
  tokenState: TokenStatus;
  isDefaultToken: boolean;
}

export interface IContainsToken {
  cardNetwork: CardNetwork;
  panLast4: string;
  tokenServiceProvider: string;
}

export interface IUserAddress {
  address1: string;
  address2: string;
  countryCode: string;
  city: string;
  state: string;
  name: string;
  phoneNumber: string;
  postalCode: string;
}

export interface IProvisionByEncryptedCard {
  encryptedCard: string;
  instCode: string;
  provisionInfo: IProvisionInfo;
}

export interface IProvisionByPushReceiptId {
  pushReceiptId: string;
  instCode: string;
  provisionInfo: IProvisionInfo;
}

export interface IProvisionInfo {
  tokenServiceProvider: TokenServiceProvider;
  cardNetwork: CardNetwork;
  displayName: string;
  lastDigits: string;
  userAddress: IUserAddress;
}

export interface IProvisioningResult {
  wasSuccessful: boolean;
  tokenId?: string;
}

export interface ITokenizeGP {
  tokenReferenceId: string;
  provisionInfo: IProvisionInfo;
}

/* enums */
export enum TokenServiceProvider {
  AMEX = 'AMEX',
  MASTERCARD = 'MASTERCARD',
  VISA = 'VISA',
}

export enum TokenStatus {
  ID_AND_V_REQUIRED = 'ID_AND_V_REQUIRED',
  PENDING = 'PENDING',
  SUSPENDED = 'SUSPENDED',
  ACTIVE = 'ACTIVE',
  FELICA_PENDING_PROVISIONING = 'FELICA_PENDING_PROVISIONING',
  UNTOKENIZED = 'UNTOKENIZED',
}

export enum CardNetwork {
  AMEX = 'AMEX',
  MASTERCARD = 'MASTERCARD',
  VISA = 'VISA',
}

export interface IssuerGooglePay {
  init(): Promise<IssuerGpApi<string | DefaultErrorApi>>;
  isAvailable(): Promise<IssuerGpApi<boolean | DefaultErrorApi>>;
  getTokens(): Promise<IssuerGpApi<IGooglePayToken | DefaultErrorApi>>;
  containsToken(
    params: IContainsToken,
  ): Promise<IssuerGpApi<boolean | DefaultErrorApi>>;
  tokenize(
    params: ITokenizeGP,
  ): Promise<IssuerGpApi<IProvisioningResult | DefaultErrorApi>>;

  executeProvisioning(
    params: IProvisionByPushReceiptId,
  ): Promise<IssuerGpApi<IProvisioningResult | DefaultErrorApi>>;

  executeProvisioningOfEncryptedCard(
    params: IProvisionByEncryptedCard,
  ): Promise<IssuerGpApi<IProvisioningResult | DefaultErrorApi>>;
}
