function mapsConfig(uiGmapGoogleMapApiProvider) {
    uiGmapGoogleMapApiProvider.configure({
        key: 'AIzaSyBaXE1N1IWzlv3Gg_PRVvaYJurBjEWE5ak',
        libraries: 'places'
    });
};

mapsConfig.$inject = ['uiGmapGoogleMapApiProvider'];
export default mapsConfig;
