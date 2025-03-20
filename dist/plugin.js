var capacitorCapacitorIssuerSdk = (function (exports, core) {
    'use strict';

    exports.Status = void 0;
    (function (Status) {
        Status["SUCCESS"] = "SUCCESS";
        Status["FAILURE"] = "FAILURE";
    })(exports.Status || (exports.Status = {}));
    /* enums */
    exports.TokenServiceProvider = void 0;
    (function (TokenServiceProvider) {
        TokenServiceProvider["AMEX"] = "AMEX";
        TokenServiceProvider["MASTERCARD"] = "MASTERCARD";
        TokenServiceProvider["VISA"] = "VISA";
    })(exports.TokenServiceProvider || (exports.TokenServiceProvider = {}));
    exports.TokenStatus = void 0;
    (function (TokenStatus) {
        TokenStatus["ID_AND_V_REQUIRED"] = "ID_AND_V_REQUIRED";
        TokenStatus["PENDING"] = "PENDING";
        TokenStatus["SUSPENDED"] = "SUSPENDED";
        TokenStatus["ACTIVE"] = "ACTIVE";
        TokenStatus["FELICA_PENDING_PROVISIONING"] = "FELICA_PENDING_PROVISIONING";
        TokenStatus["UNTOKENIZED"] = "UNTOKENIZED";
    })(exports.TokenStatus || (exports.TokenStatus = {}));
    exports.CardNetwork = void 0;
    (function (CardNetwork) {
        CardNetwork["AMEX"] = "AMEX";
        CardNetwork["MASTERCARD"] = "MASTERCARD";
        CardNetwork["VISA"] = "VISA";
    })(exports.CardNetwork || (exports.CardNetwork = {}));

    const IssuerGP = core.registerPlugin('CapacitorIssuerGooglePay', {
        web: () => Promise.resolve().then(function () { return web; }).then(m => new m.CapacitorIssuerSdkWeb()),
    });

    class CapacitorIssuerSdkWeb extends core.WebPlugin {
        init() {
            throw new Error('Method not implemented.');
        }
        isAvailable() {
            throw new Error('Method not implemented.');
        }
        getTokens() {
            throw new Error('Method not implemented.');
        }
        containsToken(params) {
            console.log(params);
            throw new Error('Method not implemented.');
        }
        tokenize(params) {
            console.log(params);
            throw new Error('Method not implemented.');
        }
        executeProvisioning(params) {
            console.log(params);
            throw new Error('Method not implemented.');
        }
        executeProvisioningOfEncryptedCard(params) {
            console.log(params);
            throw new Error('Method not implemented.');
        }
    }

    var web = /*#__PURE__*/Object.freeze({
        __proto__: null,
        CapacitorIssuerSdkWeb: CapacitorIssuerSdkWeb
    });

    exports.IssuerGP = IssuerGP;

    Object.defineProperty(exports, '__esModule', { value: true });

    return exports;

})({}, capacitorExports);
//# sourceMappingURL=plugin.js.map
