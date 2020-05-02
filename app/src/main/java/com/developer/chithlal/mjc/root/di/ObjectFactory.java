package com.developer.chithlal.mjc.root.di;

public  class ObjectFactory {

    private static FragmentComponent sFragmentComponent;

    public static FragmentComponent getFragmentComponent(){

            sFragmentComponent = DaggerFragmentComponent.builder()
                    .addWorkModule(new AddWorkModule())
                    .build();
            return sFragmentComponent;
    }
}
