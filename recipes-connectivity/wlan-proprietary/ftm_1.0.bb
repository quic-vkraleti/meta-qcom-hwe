inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Qualcomm Technologies ftmdaemon"

DEPENDS += "libnl diag glib-2.0 property-vault ath6kl-utils"

RDEPENDS:${PN} = "property-vault"

PV = "1.0"
PBT_ARCH = "armv8-2a"

SRC_URI[sha256sum] = "b3338f62377062fc9220d09bf4354304761e53b1f5efffa4ef83edda28b2346d"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"