package com.mimogoods.dev.tools.basic.immutable;

/**
 * @author I068378
 */
public abstract class DownloadDocumentFlowContext implements DocumentFlowContext<DownloadDomainModel> {

    @Override
    public Class<DownloadDomainModel> getModelClass() {
        return DownloadDomainModel.class;
    }
}
